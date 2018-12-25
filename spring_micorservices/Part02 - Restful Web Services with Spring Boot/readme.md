# Part 02 - Restful Web Services with Spring Boot

This sections uses Spring Boot 2.0.0, instead I'll have to use Spring Boot 2.1.1, so I'll annotate the differences when needed.

1. **Initializing a RESTful Services Project with Spring Boot**
  - Spring Initializr, Import project, Structure Overview.
2. **Creating a Hello World Service**
  - @RestController, @RequestMapping, Mapping Shortcuts, @GetMapping, Return Beans, @PathVariable.
3. **Quick Review of Spring Boot Auto Configuration and Dispatcher Servlet**
  - Debug Mode, Auto-Configuration Report, Dispatcher Servlet.
4. **Creating User Bean and User Service**
  - User, UserDaoService, @Component.
5. **Implementing GET and POST Methods**
  - UserResource, @Autowired, JSON Date Formatting, @PostMapping, @RequestBody, @ServletUriComponentsBuilder, Postman.
6. **Exception Handling**
  - 404, @ResponseStatus, Generic Exception Handling, @ExceptionHandler.
7. **Implementing DELETE Method**
  - Delete , @DeleteMapping.
8. **Data Validation**
  - Validation, @Valid, @Size, @Past, Improving Validation Error Messages.
9. **HATEOAS**
  - HATEOAS, Resource<T>, ControllerLinkBuilder.
0. **Step10 - Internationalization And Content Negotiation**
  - LocalResolver, ResourceBundleMessageSource, Using MessageSource, @RequestHeader, Content Negotiation.
1. **Step11 - Swagger Documentation**
  - Generation, @Configuration, @EnableSwagger2, Docket, DocumentationType, Documentation, Enhancing Documentation, apiInfo, @ApiModel, @ApiModelProperty.
2. **Step12 - Monitoring APIs**
