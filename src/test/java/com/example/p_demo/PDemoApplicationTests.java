package com.example.p_demo;


import com.example.p_demo.common.RedisStatic;
import com.example.p_demo.pojo.*;
import com.example.p_demo.service.FibSupplier;
import com.example.p_demo.service.RedisService;
import com.example.p_demo.service.UserInterface;
import com.example.p_demo.service.impl.MongoService;
import com.example.p_demo.service.impl.UserImpl;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.JacksonXmlModule;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

@SpringBootTest
class PDemoApplicationTests {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private RedisService redisService;
    @Autowired
    private MongoService mongoService;

    @Test
    public void mo1(){
        Books books = new Books();
        books.setId("1");
        books.setName("dada");

        Books target = new Books();
        target.setId("2");
        target.setName("测试修改");
        target.setCreateTime(new Date());
        target.setUpdateTime(new Date());

        System.out.println("已经回退Push");

        List<Books> list = new ArrayList<>();
        list.add(books);
        list.add(target);

        Map<String, Books> collect = list.stream().collect(Collectors.toMap(Books::getId, n -> n));
        System.out.println(collect);

//        mongoService.updateInsert(books,target);

//        mongoService.deleteBooks();

//        mongoService.updateBooks();

//        List<Books> booksName = mongoService.getBooksName(null);
//        for (Books books : booksName) {
//            System.out.println(books);
//        }

//        Books id = mongoService.findId();
//        System.out.println(id);

//        List<Books> all = mongoService.findAll();
//        for (Books books : all) {
//            System.out.println(books);
//        }
//        for (int i = 0; i < 10; i++) {
//            mongoService.save();
//        }
//        Books books = new Books();
//        books.setName("xiaoxiao");
//        books.setCreateTime(new Date());
//        books.setUpdateTime(new Date());
//        mongoTemplate.save(books,"run2");
    }



    @Test
    public void test17(){

        int[] str = new int[10];
        for (int i = 0; i < 10; i++) {
            str[i] = i;
        }
        System.out.println(Arrays.toString(str));
        System.out.println(Arrays.toString(Arrays.copyOf(str, 7)));
//        Queue<String> queue = new LinkedList<>();
//        queue.add("a");
//        queue.add("a");
//        queue.add("b");
//        System.out.println(queue);
//        for (String s : queue) {
//            System.out.println(queue.element());
//            System.out.println(s);
//            System.out.println(queue.remove());
//        }
//        System.out.println(queue.remove());
    }

    @Test
    public void test16() throws IOException {
        //json - > JavaBean
        InputStream input = this.getClass().getResourceAsStream("/book.json");
        ObjectMapper mapper = new ObjectMapper();
        // 反序列化时忽略不存在的JavaBean属性:
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        Book book = mapper.readValue(input, Book.class);
        System.out.println(book.id);
        System.out.println(book.name);
        System.out.println(book.author);
        System.out.println(book.isbn);
        System.out.println(book.tags);
        System.out.println(book.pubDate);

        //xml -> JavaBean
//        InputStream ra = this.getClass().getResourceAsStream("/book.xml");
//        JacksonXmlModule jacksonXmlModule = new JacksonXmlModule();
//        XmlMapper xmlMapper = new XmlMapper(jacksonXmlModule);
//        Book book = xmlMapper.readValue(ra, Book.class);
//
//        System.out.println(book.id);
//        System.out.println(book.name);
//        System.out.println(book.author);
//        System.out.println(book.isbn);
//        System.out.println(book.tags);
//        System.out.println(book.pubDate);
    }

    @Test
    public void test15(){
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        System.out.println(calendar.get(Calendar.MONTH));
        System.out.println(calendar.get(Calendar.YEAR));
        calendar.add(Calendar.YEAR,-1);
        System.out.println(calendar.get(Calendar.YEAR));
    }

    @Test
    public void test14(){
        SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        Date d1 = beginDayTime(date,0);
        System.out.println(sm.format(d1.getTime()));
        Date d2 = beginDayTime(date, 1);
        System.out.println(sm.format(d2.getTime()));

        Date d3 = beginWeekTime(date,0);
        System.out.println(sm.format(d3.getTime()));
        Date d5 = beginWeekTime(date, 1);
        System.out.println(sm.format(d5.getTime()));

        Date d6 = beginYearTime(date,0);
        System.out.println(sm.format(d6.getTime()));
        Date d7 = beginYearTime(date, 1);
        System.out.println(sm.format(d7.getTime()));
    }

    public Date beginYearTime(Date date,int count){
        if (date == null) return null;
        Calendar calendar = null;
        if (count == 0 ){
            calendar = Calendar.getInstance();
            calendar.setTime(date);
//            calendar.set(Calendar.YEAR,calendar.get(Calendar.YEAR));
//            calendar.add(Calendar.YEAR, 0);
            //设置为1号,当前日期既为本年第一天
            calendar.set(Calendar.DAY_OF_YEAR,1);
            calendar.set(Calendar.HOUR_OF_DAY,0);
            calendar.set(Calendar.MINUTE,0);
            calendar.set(Calendar.SECOND,0);
            calendar.set(Calendar.MILLISECOND,0);
        }else {
            calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.set(Calendar.DAY_OF_YEAR,calendar.getActualMaximum(Calendar.DAY_OF_YEAR));
            calendar.set(Calendar.HOUR_OF_DAY,23);
            calendar.set(Calendar.MINUTE,59);
            calendar.set(Calendar.SECOND,59);
            calendar.set(Calendar.MILLISECOND,59);
        }
        return calendar.getTime();
    }

    public Date beginWeekTime(Date date,int count){
        if (date == null) return null;
        Calendar calendar = null;
        if (count == 0 ){
            calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.set(Calendar.WEEK_OF_MONTH,1);
            calendar.set(Calendar.HOUR_OF_DAY,0);
            calendar.set(Calendar.MINUTE,0);
            calendar.set(Calendar.SECOND,0);
            calendar.set(Calendar.MILLISECOND,0);
        }else {
            calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
            calendar.set(Calendar.HOUR_OF_DAY,23);
            calendar.set(Calendar.MINUTE,59);
            calendar.set(Calendar.SECOND,59);
            calendar.set(Calendar.MILLISECOND,59);
        }
        return calendar.getTime();
    }

    public Date beginDayTime(Date date,int count){
        if (date == null){
            return null;
        }
        Calendar calendar = null;
        if (count == 0){
            calendar = Calendar.getInstance();
            calendar.clear();
            calendar.setTime(date);
            calendar.setFirstDayOfWeek(Calendar.MONDAY);
            calendar.set(Calendar.DAY_OF_WEEK,Calendar.MONDAY);
            calendar.set(Calendar.HOUR_OF_DAY,0);
            calendar.set(Calendar.MINUTE,0);
            calendar.set(Calendar.SECOND,0);
        }else {
            calendar = Calendar.getInstance();
            calendar.clear();
            calendar.setTime(date);
            calendar.setFirstDayOfWeek(Calendar.MONDAY);
            calendar.set(Calendar.DAY_OF_WEEK,Calendar.SUNDAY);
            calendar.set(Calendar.HOUR_OF_DAY,23);
            calendar.set(Calendar.MINUTE,59);
            calendar.set(Calendar.SECOND,59);
        }

        return calendar.getTime();
    }

    /**
     *
     * @param
     * @param
     */
    @Test
    public void test13(){

//        Calendar calendar = Calendar.getInstance();
//        Date now = calendar.getTime();
//        System.out.println(sm.format(now));
//        calendar.clear();
//        calendar.set(Calendar.YEAR,2020);
//        calendar.set(Calendar.DAY_OF_MONTH,-3);
//        calendar.set(Calendar.HOUR_OF_DAY,-1);
//        Date t2 = calendar.getTime();
//        System.out.println(sm.format(t2));
//        System.out.println(now.before(t2));

//        ArrayList<Integer> list = new ArrayList<>();
//        list.add(new Integer(111));

//        Logger logger = Logger.getGlobal();
//        logger.info("start process...");
//        logger.warning("memory is running out...");
//        logger.fine("ignored.");
//        logger.severe("process will be terminated...");

//        int i = 100;
//        assert i>0:"正确";
//        System.out.println(i);
//        for (int i = 0; i < 100; i++) {2
//            int count = (int)(Math.random()*(16-14))+14;
//            System.out.println(count);
//        }

//        BigInteger bi = new BigInteger("1234567890000000");
//        long i = bi.longValue();
//        System.out.println(bi.intValueExact());

//        Integer integer = Integer.valueOf("2321321");
//        int i = Integer.parseInt("323123");
//        String name = ResultEnum.FAIL.name();
//        String message = ResultEnum.FAIL.getMessage();
//        Integer code = ResultEnum.FAIL.getCode();
//        System.out.println(name);
//        System.out.println(message);
//        System.out.println(code);
    }

    @Test
    public void test12(){
        System.out.println(StringUtils.isNotBlank(" "));
        String[] fields = { "name", "position", "salary" };
        String table = "employee";
        String select = buildSelectSql(table, fields);
        System.out.println(select);
        System.out.println("SELECT name, position, salary FROM employee".equals(select) ? "测试成功" : "测试失败");
    }

    static String buildSelectSql(String table, String[] fields) {
        // TODO:
        StringJoiner sj = new StringJoiner(", ","SELECT "," FROM "+table);
        for (int i = 0; i < fields.length; i++) {
            sj.add(fields[i]);
        }
        return sj.toString();
    }


    @Test
    public void test11(){
        String[] fields = { "name", "position", "salary" };
        String table = "employee";
        String insert = buildInsertSql(table, fields);
        System.out.println(insert);
        String s = "INSERT INTO employee (name, position, salary) VALUES (?, ?, ?)";
        System.out.println(s.equals(insert) ? "测试成功" : "测试失败");

    }

    static String buildInsertSql(String table, String[] fields) {
        // TODO:
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO ")
                .append(table)
                .append(" (");
        for (int i = 0; i < fields.length; i++) {
            sb.append(fields[i])
                .append(", ")
            ;
        }
        sb.delete(sb.length()-2,sb.length())
                .append(")")
                .append(" VALUES (?, ?, ?)");
//        int i = sb.lastIndexOf(",");
//        String substring = sb.substring(0, i);
//        substring = substring +") VALUES (?, ?, ?)";
        return sb.toString();
    }

    @Test
    public void test10(){
//        System.out.println("\tabc\r\ncc");
        String s = "Hi %s, your score is %.3f!";
        System.out.println(String.format(s, "abc",4390.1));
        System.out.println(Integer.getInteger("java.version"));
        StringBuilder sb = new StringBuilder();
//        sb.append()
    }

    @Test
    public void test9(){

        UserInterface ut = new UserImpl();
        String test = ut.test();
        System.out.println(test);
        User user = new User();
        Person person = new Person();
        Person studer = new Studer();
        System.out.println(person instanceof Studer);
        System.out.println(studer instanceof Studer);

    }

    @Test
    public void test8(){
        String[] str = {"aaa","bbb","ccc"};
        stringCome(str);
    }


    public void stringCome(String... users){
        List<String> list = new ArrayList<>();
        for (int i = 0; i < users.length; i++) {
            System.out.println(users[i]);
        }
    }

    @Test
    public void test7(){
        redisService.set(RedisStatic.user_Cache+6,"fldj ");
        Object o = redisService.get(RedisStatic.user_Cache + "6");
        System.out.println(o);
        Long expire = redisService.getExpire(RedisStatic.user_Cache + "6");
        System.out.println(expire);
    }

    @Test
    public void test6() {
        String str = "APPL:Apple";
        Map<String, String> collect = Stream.of(str).collect(Collectors.toMap(
                s -> s.substring(0, s.indexOf(":")),
                s -> s.substring(s.indexOf(":") + 1)
        ));
        System.out.println(collect);
        System.out.println(str.substring(0,str.indexOf(":")));
        System.out.println(str.substring(str.indexOf(":")));

//        String[] str = {"a", "", "aa", "a", "c", " "};
//        Map<String, String> map = new HashMap<>();
//        Set<String> collect = Stream.of(str).filter(n -> n != null && !n.isEmpty()).collect(Collectors.toSet());
//        System.out.println(collect);
        // 按行读取配置文件:
//        List<String> props = List.of("profile=native", "debug=true", "logging=warn", "interval=500");
//        Map<String, String> map = props.stream()
//                // 把k=v转换为Map[k]=v:
//                .map(kv -> {
//                    String[] ss = kv.split("\\=", 2);
//                    return Map.of(ss[0], ss[1]);
//                })
//                // 把所有Map聚合到一个Map:
//                .reduce(new HashMap<String, String>(), (m, kv) -> {
//                    m.putAll(kv);
//                    return m;
//                });
//        // 打印结果:
//        map.forEach((k, v) -> {
//            System.out.println(k + " = " + v);
//        });

//        String[] strings = new String[]{"profile=native", "debug=true", "logging=warn", "interval=500"};
//        List<String> collect = Stream.of(strings).collect(Collectors.toList());
//        Map<String, String> reduce = collect.stream()
//                .map(kv -> {
//                    String[] split = kv.split("\\=", 2);
//                    Map<String, String> map = new HashMap<>();
////            return map.put(split[0],split[1]);
//                    return Collections.singletonMap(split[0], split[1]);
//                }).reduce(new HashMap<String, String>(), (m, kv) -> {
//                    m.putAll(kv);
//                    return m;
//                });
//        reduce.forEach((k,v)->{
//            System.out.println(k +"---"+v);
//        });
//        Stream<String> stream = Stream.of();
//        stream.map(kv -> {
//            String[] split = kv.split("\\=", 2);
//            Map<String,String> map = new HashMap<>();
//            return map.put(split[0],split[1]);
//        }).reduce(String.valueOf(new HashMap<String,String>()),(m, kv) -> {
//                    return m;
//                });
//        String str= "fjdljfdls";
//        String[] fs = str.split("f");
//        Stream<String> stream = Arrays.stream(fs);
//        System.out.println(fs.length);
//        stream.forEach(System.out::println);
    }

    @Test
    public void test5() {
        Stream<Integer> integerStream = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9);
        Optional<Integer> reduce = integerStream.reduce((acc, n) -> acc + n);
        System.out.println(reduce.isPresent());
        if (reduce.isPresent()) {
            System.out.println(reduce.get());
        }
//                .reduce(0, (acc, n) -> acc + n);
//        System.out.println(sum); // 45
        LongStream stream = LongStream.generate(new FibSupplier());
        stream.limit(100).forEach(System.out::println);
    }

    @Test
    public void test3() {
        IntStream stream = Arrays.stream(new int[]{0, 1});
        stream.map(n -> n * n).forEach(System.out::println);
//        Stream.iterate(new long[]{0, 1}, a -> new long[]{a[1], a[0] + a[1]})
//                .limit(100)
//                .map(a -> a[1] + ",")
//                .forEach(System.err::print);

//        Pattern p = Pattern.compile("\\s+");
//        Stream<String> s = p.splitAsStream("The quick brown fox jumps over the lazy dog");
//        s.forEach(System.out::println);

//        Stream<String> stream = Stream.of("A", "B", "C", "D");
//        // forEach()方法相当于内部循环调用，
//        // 可传入符合Consumer接口的void accept(T t)的方法引用：
//        System.out.println(stream.count());
////        stream.forEach(System.out::println);
//        List<Integer> list = new ArrayList<>();
//        for (int i = 0; i < 100; i++) {
//            list.add(i);
//        }
//        list.stream();
    }


    @Test
    public void test() {
        String[] array = new String[]{"Apple", "Orange", "Banana", "Lemon"};
        Stream<String> stream = Arrays.stream(array);
        stream.forEach(System.out::println);
        Arrays.sort(array, String::compareTo);
        System.out.println(String.join(", ", array));
//        List<String> names = List.of("Bob", "Alice", "Tim");
//        List<String> names = new ArrayList<>();
//        names.add("Bob");
//        names.add("Alice");
//        names.add("Tim");
//        List<Person> persons = new ArrayList<>();
//        for (String name : names) {
//            persons.add(new Person(name));
//        }
//        System.out.println(persons);
//        String[] array = new String[] { "Apple", "Orange", "Banana", "Lemon" };
//        Arrays.sort(array, (s1, s2) -> {
//            return s1.compareTo(s2);
//        });
//        System.out.println(String.join(", ", array));

    }

    @Test
    public void test2() {
        Comparator<String> stringComparator = new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return 0;
            }

            @Override
            public boolean equals(Object obj) {
                return false;
            }
        };
        ;
//        String[] array = ...
//        Arrays.sort(array, new Comparator<String>() {
//            public int compare(String s1, String s2) {
//                return s1.compareTo(s2);
//            }
//        });
    }

    @Test
    void contextLoads() {
    }

}
