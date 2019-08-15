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





/*    @Bean
    public HibernateItemWriter<Planet> writer(SessionFactory sessionFactory) {
        return new HibernateItemWriterBuilder<Planet>()
                .sessionFactory ( sessionFactory )
                .build();
    }*/

    @Bean
    public JdbcBatchItemWriter<Planet> writer(DataSource dataSource) {
        return new JdbcBatchItemWriterBuilder <Planet> ()
                .itemSqlParameterSourceProvider ( new BeanPropertyItemSqlParameterSourceProvider <> () )
                .sql ( "INSERT INTO planet (planet_node, planet_name) VALUES (:planet_node, :planet_name)" )
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
    @Primary
    public ItemProcessor processor() {
        return new ItemProcessor ();
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
    public Job importUserJob(JobCompletionNotificationListener listener,Step step1) {
        return jobBuilderFactory.get("importUserJob")
                .incrementer(new RunIdIncrementer ())
                .listener(listener)
                .flow(step1)
                .end()
                .build();
    }

}
