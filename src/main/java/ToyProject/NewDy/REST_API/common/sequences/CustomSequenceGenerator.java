package ToyProject.NewDy.REST_API.common.sequences;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.Type;

import java.util.Properties;
import java.util.UUID;
import java.util.stream.Stream;

/**
 * 커스텀 시퀀스 제네레이터
 * prefix 형태로 어떤 키인가를 명시적으로 나타내기 위해서 생성
 */
public class CustomSequenceGenerator implements IdentifierGenerator {

    private String prefix;

    @Override
    public void configure(Type type, Properties parameters, ServiceRegistry serviceRegistry) {
        IdentifierGenerator.super.configure(type, parameters, serviceRegistry);
        prefix = parameters.getProperty("prefix");
    }

    @Override
    public Object generate(SharedSessionContractImplementor session, Object object) {
//        String query = String.format("select %s from %s",
//                session.getEntityPersister(object.getClass().getName(), object)
//                        .getIdentifierPropertyName(),
//                object.getClass().getSimpleName());

//        Stream<String> ids = session.createQuery(query, String.class).stream();
//
//        Long max = ids.map(o -> o.replace(prefix + "_", ""))
//                .mapToLong(Long::parseLong)
//                .max()
//                .orElse(0L);
        UUID uuid = UUID.randomUUID();

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(prefix);
        stringBuilder.append("_");
        stringBuilder.append(uuid);

        return stringBuilder.toString();
    }
}
