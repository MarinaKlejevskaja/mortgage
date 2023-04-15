package com.academy.mortgage.serializers;

import com.academy.mortgage.model.Users;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.hibernate.proxy.HibernateProxy;

import java.io.IOException;

public class UsersSerializer  extends StdSerializer<Users> {

    public UsersSerializer() {
        this(null);
    }

    public UsersSerializer(Class<Users> usersClass) {
        super(usersClass);
    }

    @Override
    public void serialize(Users user, JsonGenerator jgen, SerializerProvider provider) throws IOException {
        if (user instanceof HibernateProxy) {
            user = (Users) ((HibernateProxy) user).getHibernateLazyInitializer().getImplementation();
        }

        jgen.writeStartObject();
        jgen.writeNumberField("id", user.getId());
        jgen.writeEndObject();
    }
}
