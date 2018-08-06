package com.other.intf;

import java.util.Objects;

@FunctionalInterface
public interface PeopleFilter{
    boolean accept(People people);

     default PeopleFilter and(PeopleFilter other){
         Objects.requireNonNull(other);
         return people -> accept(people) && other.accept(people);
     }
}
