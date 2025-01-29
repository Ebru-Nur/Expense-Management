
# Base Image
FROM openjdk:17-jdk-slim

# Çalışma dizinini ayarla
WORKDIR /app

# Maven hedef dosyasını (jar) kopyala
COPY target/*.jar app.jar

# Uygulama portunu aç
EXPOSE 8090

# Uygulamayı çalıştır
ENTRYPOINT ["java", "-jar", "app.jar"]
