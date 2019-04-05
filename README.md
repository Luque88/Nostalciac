# Build
mvn clean package && docker build -t it.ciacformazione.nostalciac/nostalciac2.0 .

# RUN

docker rm -f nostalciac2.0 || true && docker run -d -p 8080:8080 -p 4848:4848 --name nostalciac2.0 it.ciacformazione.nostalciac/nostalciac2.0 