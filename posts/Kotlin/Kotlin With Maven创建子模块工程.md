# Kotlin With Maven创建子模块工程
## 父模块
```XML
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xmlns="http://maven.apache.org/POM/4.0.0"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>3.1.1</version>
        <relativePath/> <!-- 强制从仓库中获取父工程 -->
    </parent>
    <modules>
        <module>kt-framework</module>
        <module>kt-demo</module>
        ...
    </modules>

    <artifactId>KotlinWithMaven</artifactId>
    <groupId>moe.saikyo47</groupId>
    <packaging>pom</packaging>
    <version>1.0-SNAPSHOT</version>

    <properties>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <kotlin.code.style>official</kotlin.code.style>
        <kotlin.compiler.jvmTarget>17</kotlin.compiler.jvmTarget>
        <java.version>17</java.version>
        <kotlin.version>1.8.20</kotlin.version>
    </properties>

    <dependencyManagement>
        <dependencies>
            <!--Kotlin标准库-->
            <dependency>
                <groupId>org.jetbrains.kotlin</groupId>
                <artifactId>kotlin-test-junit5</artifactId>
                <version>${kotlin.version}</version>
                <scope>test</scope>
            </dependency>
            <dependency>
                <groupId>org.jetbrains.kotlin</groupId>
                <artifactId>kotlin-stdlib-jdk8</artifactId>
                <version>${kotlin.version}</version>
            </dependency>

            <dependency>
                <groupId>org.junit.jupiter</groupId>
                <artifactId>junit-jupiter-engine</artifactId>
                <version>5.8.2</version>
                <scope>test</scope>
            </dependency>
            ...
        </dependencies>
    </dependencyManagement>

    <build>
        <pluginManagement>
            <plugins>
                <plugin>
                    <groupId>org.springframework.boot</groupId>
                    <artifactId>spring-boot-maven-plugin</artifactId>
                </plugin>
                <plugin>
                    <groupId>org.jetbrains.kotlin</groupId>
                    <artifactId>kotlin-maven-plugin</artifactId>
                    <version>${kotlin.version}</version>
                    <configuration>
                        <args>
                            <!-- 对JSR-305 注解启用严格模式-->
                            <arg>-Xjsr305=strict</arg>
                        </args>
                        <compilerPlugins>
                            <!--将Spring相关注解标记的类指定为open-->
                            <plugin>spring</plugin>
                            <!--将特定注解标记的类指定为open-->
                            <plugin>all-open</plugin>
                            <!-- 对 JPA 的情况请使用 "jpa" 插件 -->
                            <plugin>no-arg</plugin>
                        </compilerPlugins>
                        <pluginOptions>
                            <!-- 指定为open的注解 -->
<!--                            <option>all-open:annotation=com.my.Annotation</option>-->
                            <!--指定生成无参构造器的注解-->
<!--                            <option>no-arg:annotation=com.my.Annotation</option>-->
                        </pluginOptions>
                        <jvmTarget>17</jvmTarget>
                    </configuration>
                    <dependencies>
                        <!--all-open插件-->
                        <dependency>
                            <groupId>org.jetbrains.kotlin</groupId>
                            <artifactId>kotlin-maven-allopen</artifactId>
                            <version>${kotlin.version}</version>
                        </dependency>
                        <!--no-arg插件-->
                        <dependency>
                            <groupId>org.jetbrains.kotlin</groupId>
                            <artifactId>kotlin-maven-noarg</artifactId>
                            <version>1.8.20</version>
                        </dependency>
                    </dependencies>
                    <executions>
                        <execution>
                            <id>compile</id>
                            <goals>
                                <goal>compile</goal>
                            </goals>
                            <configuration>
                                <sourceDirs>
                                    <!--Kotlin项目目录-->
                                    <sourceDir>src/main/kotlin</sourceDir>
                                    <!--Java混合项目目录-->
<!--                                    <sourceDir>src/main/java</sourceDir>-->
                                </sourceDirs>
                            </configuration>
                        </execution>
                        <execution>
                            <id>test-compile</id>
                            <goals>
                                <goal>test-compile</goal>
                            </goals>
                            <configuration>
                                <sourceDirs>
                                    <!--Kotlin项目目录-->
                                    <sourceDir>src/test/kotlin</sourceDir>
                                    <!--Java混合项目目录-->
<!--                                    <sourceDir>src/test/java</sourceDir>-->
                                </sourceDirs>
                            </configuration>
                        </execution>
                    </executions>
                </plugin>
            </plugins>
        </pluginManagement>
    </build>
</project>
```

## 公共子模块
```XML
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <parent>
        <groupId>moe.saikyo47</groupId>
        <artifactId>KotlinWithMaven</artifactId>
        <version>1.0-SNAPSHOT</version>
        <relativePath>../pom.xml</relativePath>
    </parent>

    <artifactId>kt-framework</artifactId>
    <packaging>jar</packaging>

    <dependencies>
        <dependency>
            <groupId>com.fasterxml.jackson.module</groupId>
            <artifactId>jackson-module-kotlin</artifactId>
        </dependency>
        <dependency>
            <groupId>org.jetbrains.kotlin</groupId>
            <artifactId>kotlin-reflect</artifactId>
        </dependency>
        <dependency>
            <groupId>org.jetbrains.kotlin</groupId>
            <artifactId>kotlin-stdlib</artifactId>
        </dependency>
        ...
    </dependencies>


    <build>
        <plugins>
            <plugin>
                <groupId>org.jetbrains.kotlin</groupId>
                <artifactId>kotlin-maven-plugin</artifactId>
            </plugin>
        </plugins>
    </build>
</project>
```

## 功能子模块
```XML
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <parent>
        <groupId>moe.saikyo47</groupId>
        <artifactId>KotlinWithMaven</artifactId>
        <version>1.0-SNAPSHOT</version>
        <relativePath>../pom.xml</relativePath>
    </parent>

    <artifactId>kt-demo</artifactId>
    <packaging>jar</packaging>

    <dependencies>
        <dependency>
            <groupId>moe.saikyo47</groupId>
            <artifactId>kt-framework</artifactId>
            <version>1.0-SNAPSHOT</version>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
            <plugin>
                <groupId>org.jetbrains.kotlin</groupId>
                <artifactId>kotlin-maven-plugin</artifactId>
            </plugin>
        </plugins>
    </build>
</project>
```

## Q&A
### 系统找不到指定的文件
>Kotlin: [Internal Error] java.io.FileNotFoundException: C:\Users\smile\AppData\Local\JetBrains\IntelliJIdea2023.1\kotlin-dist-for-ide\1.8.20\lib\kotlin-compiler.jar (系统找不到指定的文件。)

在设置-Kotlin编译器中将Kotlin编译器版本设置为**捆绑的**

### 无法自动装配
将mapper等目录直接放在**groupId**下，Kotlin创建新模块时会在groupId的软件包下创建次级目录（如子模块demo则src->main->kotlin->com->example->demo）导致spring读不到beans

