java -jar openapi-generator-cli.jar generate \
-i application.yml \
-c config.yml \
-g spring \
--additional-properties=waggerAnnotations=true,serviceImplementation=true \
--import-mappings=DateTime=java.time.LocalDateTime \
--type-mappings=DateTime=java.time.LocalDateTime \
-o .. \