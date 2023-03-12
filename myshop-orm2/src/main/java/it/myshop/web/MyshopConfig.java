package it.myshop.web;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

import it.myshop.web.dao.ClienteDao;
import it.myshop.web.dao.impl.ClienteDaoImpl;


@EnableWebMvc
@Configuration
@ComponentScan("it.myshop.web.controller")
@EnableTransactionManagement
@EnableJpaRepositories
public class MyshopConfig
{

	
	//definiamo il view resolver	
	@Bean
	public FreeMarkerViewResolver configureResolver()
	{
		FreeMarkerViewResolver resolver = new FreeMarkerViewResolver();
		resolver.setPrefix("");
		resolver.setSuffix(".ftl");
		return resolver;		
	}
	
	@Bean
	public FreeMarkerConfigurer configureFreeMarker()
	{
		FreeMarkerConfigurer config = new FreeMarkerConfigurer();
		config.setTemplateLoaderPath("/WEB-INF/view/");
		
		return config;		
	}
	
	
	
	//bean per la connessione al database - usando connessione JDBC
	@Bean
	public DataSource getDBConnection() 
	{
		//connector verso il database - usando connessione JDBC
		DriverManagerDataSource ds = new DriverManagerDataSource();
		
		ds.setDriverClassName("org.mariadb.jdbc.Driver");
		ds.setUsername("root"); // possbile utlizzare file di property
		ds.setPassword("root");  // possbile utlizzare file di property
		ds.setUrl("jdbc:mariadb://localhost:3306/corso_spring");
		
		
		return ds;
	}
	
	
	//consente di accedere ai metodi persist salvataggio get ecc
	//crea un entiry manager di tipo hibertnate per mysql(mariDB)
	@Bean(name = "entityManagerFactory")
	public LocalContainerEntityManagerFactoryBean getEntityManager()
	{
		HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
		adapter.setDatabase(Database.MYSQL);
		//adapter.setGenerateDdl(true); //crea o aggiorna le tabelle mappate con gli entitty
		
		LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
		factory.setDataSource(getDBConnection());
		factory.setJpaVendorAdapter(adapter);
		factory.setPackagesToScan(getClass().getPackage().getName()); // package nei quali ci sono le classi dao
		
		return factory;
	}
	
	@Bean(name = "transactionManager")
	public PlatformTransactionManager getTRansactionmaManager()
	{
		JpaTransactionManager jtm = new JpaTransactionManager();
		jtm.setEntityManagerFactory(getEntityManager().getObject());
		return jtm;
	}
	
//	//beans per la definizione dei dao	
	@Bean
	public ClienteDao  getClienteService()
	{
		return new ClienteDaoImpl();
	}
//	
//	@Bean
//	public CategoriaDao getCategoriaService()
//	{
//		return new CateogoriaDaoImpl( getDBConnection() ) ;
//	}
//	
//	@Bean
//	public ProdottoDao getProdottoService()
//	{
//		return new ProdottoDaoImpl( getDBConnection() );
//	}
//	
	
}
