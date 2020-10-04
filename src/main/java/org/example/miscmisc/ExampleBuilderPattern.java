package org.example.miscmisc;

public class ExampleBuilderPattern {
    private String name;
    private String id;

    public ExampleBuilderPattern(Builder builder) {//5
        this.name = builder.name;
        this.id = builder.id;
    }
    public static Builder builder(){      //1
        return new Builder();
    }
    public static Builder builder(ExampleBuilderPattern copy){ //5
        Builder builder = new Builder();
        builder.name = copy.name;
        builder.id = copy.id;
        return builder;
    }

    @Override
    public String toString() {
        return "ExampleBuilderPattern{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                '}';
    }

    public static class Builder {
        private String name;
        private String id;

        public ExampleBuilderPattern build() {    //4
            return new ExampleBuilderPattern(this);
        }

        public Builder name(String name){  //2
            this.name = name;
            return this;
        }
        public Builder id(String id){     //3
            this.id = id;
            return this;
        }
    }

    public static void main(String[] args) {
        ExampleBuilderPattern ex = ExampleBuilderPattern.builder()
                .id("iidd").name("nnaammee").build();
        System.out.println("Result: ");
        System.out.println(ex);
    }

}
