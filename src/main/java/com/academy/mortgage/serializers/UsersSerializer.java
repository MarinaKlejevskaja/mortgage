package com.academy.mortgage.serializers;

import com.academy.mortgage.model.User;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.hibernate.proxy.HibernateProxy;

import java.io.IOException;

public class UsersSerializer extends StdSerializer<User> {

    public UsersSerializer() {
        this(null);
    }

    public UsersSerializer(Class<User> usersClass) {
        super(usersClass);
    }

    @Override
    public void serialize(User user, JsonGenerator jgen, SerializerProvider provider) throws IOException {
        if (user instanceof HibernateProxy) {
            user = (User) ((HibernateProxy) user).getHibernateLazyInitializer().getImplementation();
        }

        jgen.writeStartObject();
        jgen.writeNumberField("id", user.getId());
        jgen.writeEndObject();
    }
}
