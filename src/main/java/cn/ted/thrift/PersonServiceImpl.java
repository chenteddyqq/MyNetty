package cn.ted.thrift;

import org.apache.thrift.TException;
import thrift.generated.DataException;
import thrift.generated.Person;

public class PersonServiceImpl implements thrift.generated.PersonService.Iface {
    @Override
    public Person getPersonByUsername(String name) throws DataException, TException {
        System.out.println("Got client Param : "+name);
        Person person = new Person();
        person.setUsername(name);
        person.setAge(10);
        person.setMarried(false);
        return person;
    }

    @Override
    public void savePerson(Person person) throws DataException, TException {
        System.out.println("Got client Param : ");
        System.out.println(person.getUsername());
        System.out.println(person.getAge());
        System.out.println(person.isMarried());
    }
}
