package za.co.ssquared.assignment.planet.FileProcessor;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import za.co.ssquared.assignment.planet.model.Planet;
import za.co.ssquared.assignment.planet.model.Routes;

import javax.sql.DataSource;

@Configuration
@EnableBatchProcessing
public class FileImportJob {
    /**
     * Create a new builder initialized with any properties in the parent. The parent is copied, so it can be re-used.
     *
     * @param parent the parent to use
     */

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Bean
    public JdbcBatchItemWriter<Planet> writer(DataSource dataSource) {
        return new JdbcBatchItemWriterBuilder <Planet> ()
                .itemSqlParameterSourceProvider ( new BeanPropertyItemSqlParameterSourceProvider <> () )
                .sql ( "INSERT INTO planet (planet_node, planet_name) VALUES (:planet_node, :planet_name)" )
                .dataSource ( dataSource )
                .build ();
    }

    @Bean
    public JdbcBatchItemWriter<Routes> itemWriter(DataSource dataSource) {
        return new JdbcBatchItemWriterBuilder <Routes> ()
                .itemSqlParameterSourceProvider ( new BeanPropertyItemSqlParameterSourceProvider <> () )
                .sql (  "INSERT INTO routes (route, planet_origin,planet_destination,distance) VALUES (:route,:planet_origin,:planet_destination,:distance)" )
                .dataSource ( dataSource )
                .build ();
    }


    @Bean
    public FlatFileItemReader<Planet> reader() {
        return new FlatFileItemReaderBuilder<Planet>()
                .name("planetItemReader")
                .linesToSkip ( 1 )
                .resource(new ClassPathResource ("data/planets.txt"))
                .delimited().delimiter ( "|" )
                .names(new String[]{"planet_node", "planet_name"})
                .fieldSetMapper(new BeanWrapperFieldSetMapper<Planet> () {{
                    setTargetType(Planet.class);
                }})
                .build();
    }

    @Bean
    public FlatFileItemReader<Routes> readerRoute() {
        return new FlatFileItemReaderBuilder<Routes>()
                .name("routesItemReader")
                .linesToSkip ( 1 )
                .resource(new ClassPathResource ("data/routes.txt"))
                .delimited().delimiter ( "|" )
                .names(new String[]{"route", "planet_origin","planet_destination","distance"})
                .fieldSetMapper(new BeanWrapperFieldSetMapper<Routes> () {{
                    setTargetType(Routes.class);
                }})
                .build();
    }

    @Bean
    @Primary
    public ItemProcessor processor() {
        return new ItemProcessor ();
    }

    @Bean
    @Primary
    public ItemRouteProcessor process() {
        return new ItemRouteProcessor ();
    }


    @Bean
    public Step step(JdbcBatchItemWriter<Planet> writer) {
        return stepBuilderFactory.get("step1")
                .<Planet, Planet> chunk(10)
                .reader(reader())
                .processor ( processor () )
                .writer(writer)
                .build();
    }

    @Bean
    public Step step1(JdbcBatchItemWriter<Routes> itemWriter){
        return stepBuilderFactory.get ( "step2" )
                .<Routes,Routes>chunk ( 10 )
                .reader(readerRoute())
                .processor ( process () )
                .writer ( itemWriter )
                .build ();

    }


    @Bean
    public Job importPlanetJob(JobCompletionNotificationListener listener,Step step ) {
        return jobBuilderFactory.get("importPlanetJob")
                .incrementer(new RunIdIncrementer ())
                .listener(listener)
                .flow(step)
                .end()
                .build();
    }

    @Bean
    public Job importRouteJob(JobCompletionNotificationListener listener,Step step1 ) {
        return jobBuilderFactory.get("importRouteJob")
                .incrementer(new RunIdIncrementer ())
                .listener(listener)
                .flow(step1)
                .end()
                .build();
    }

}
