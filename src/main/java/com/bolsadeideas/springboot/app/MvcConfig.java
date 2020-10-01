package com.bolsadeideas.springboot.app;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/*Esta clase es de configuracion para poder mapear la ruta del directorio donde se almacenaran
 * las imagenes, esto se hace sobreescribiendo el metodo addResourceHandler añadiendole el mapeo
 * esto con el fin de que las imagenes carguen sin tener que actualizar el directorio manualmente*/

@Configuration
public class MvcConfig implements WebMvcConfigurer{

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		WebMvcConfigurer.super.addResourceHandlers(registry);
		
		registry.addResourceHandler("/uploads/**")
		.addResourceLocations("file:/opt/uploads/");
	}
	
}
