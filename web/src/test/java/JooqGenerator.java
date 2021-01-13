import org.jooq.codegen.GenerationTool;
import org.jooq.meta.jaxb.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ian
 * @date 2021/1/8 15:57
 */
public class JooqGenerator {
    public static void main(String[] args) {
//        List<ForcedType> forcedTypeList = new ArrayList<>();
//        ForcedType forcedType1 = new ForcedType()
//                .withUserType("io.vertx.core.json.JsonObject")
//                .withConverter("io.github.jklingsporn.vertx.jooq.shared.JsonObjectConverter")
//                .withExpression("someJsonObject").withTypes(".*");
//        forcedTypeList.add(forcedType1);
//        ForcedType forcedType2 = new ForcedType()
//                .withUserType("io.vertx.core.json.JsonArray")
//                .withConverter("io.github.jklingsporn.vertx.jooq.shared.JsonArrayConverter")
//                .withExpression("someJsonArray").withTypes(".*");
//        forcedTypeList.add(forcedType2);

        Configuration configuration = new Configuration()
                .withJdbc(new Jdbc()
                        .withDriver("com.mysql.jdbc.Driver")
                        .withUrl("jdbc:mysql://localhost:3306/demo?allowMultiQueries=true&useUnicode=true&characterEncoding=UTF-8&useSSL=false&serverTimezone=GMT%2B8&tinyInt1isBit=false")
                        .withUser("root") //修改成自己的数据库
                        .withPassword("123456"))
                .withGenerator(new Generator()
                        .withName("io.github.jklingsporn.vertx.jooq.generate.classic.ClassicJDBCVertxGenerator")
//                        .withName("io.github.jklingsporn.vertx.jooq.generate.classic.ClassicReactiveVertxGenerator")
                        .withDatabase(new Database()
                                .withName("org.jooq.meta.mysql.MySQLDatabase")
                                .withIncludes(".*")
                                .withExcludes("")
                                .withInputSchema("demo")
                                .withUnsignedTypes(false)
//                                .withForcedTypes(forcedTypeList)
                        )
                        .withTarget(new Target()
                                .withPackageName("com.xxx.web.jooq")
                                .withDirectory("F:\\codeGen\\jooq"))
                        .withGenerate(new Generate().withInterfaces(true).withFluentSetters(true).withDaos(true))
                        .withStrategy(new Strategy().withName("io.github.jklingsporn.vertx.jooq.generate.VertxGeneratorStrategy")));

        try {
            GenerationTool.generate(configuration);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
