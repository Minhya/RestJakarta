import exceptions.mappers.NotFoundMapper;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;
import presentation.BookResource;

import java.util.HashSet;
import java.util.Set;

@ApplicationPath("api")
public class BookApplication extends Application {


}
//    @Override
//    public Set<Class<?>> getClasses() {
//        Set<Class<?>> classes = new HashSet<Class<?>>();
//        classes.add(BookResource.class);
//        classes.add(NotFoundMapper.class);
//        return classes;
//    }