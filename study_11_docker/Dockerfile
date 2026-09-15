# 阶段1：构建 JAR 包（可选，适合需要在容器内编译的场景）
# FROM maven:3.8.5-openjdk-8-alpine AS build
# WORKDIR /app
# COPY pom.xml .
# COPY src ./src
# RUN mvn clean package -DskipTests

# 阶段2：运行 JAR 包（核心）
FROM openjdk:8

FROM nacos/nacos-server:2.3.2
FROM redis:6.2.14

# 设置时区（解决容器内时间和本地不一致的问题）
RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime && echo 'Asia/Shanghai' >/etc/timezone

# 创建工作目录
WORKDIR /app

# 复制本地 JAR 包到容器（如果用阶段1构建，这里替换为 COPY --from=build /app/target/xxx.jar /app/app.jar）
COPY target/study_11_docker-1.0-SNAPSHOT.jar testDocker.jar

# 暴露端口
EXPOSE 8081

# 启动命令（可添加 JVM 参数，比如调整内存）
#ENTRYPOINT ["java","-Xms512m","-Xmx1024m","-jar","app.jar"]
ENTRYPOINT ["java","-jar","testDocker.jar"]
