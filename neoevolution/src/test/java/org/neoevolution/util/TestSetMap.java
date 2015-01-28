package org.neoevolution.util;

import org.neoevolution.mvc.model.Neuron;
import org.springframework.util.StopWatch;

import java.util.*;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since 1.0
 */
public class TestSetMap {


    public static void main(String[] args) {

        int size = 30000;
        Set<Neuron> set = new HashSet<>(MapUtils.getSize(size));
        Set<Neuron> linkset = new LinkedHashSet<>(MapUtils.getSize(size));
        Map<Long, Neuron> map = new HashMap<>(MapUtils.getSize(size));
//        Set<Long> set = new HashSet<>();
//        Map<Long, Long> map = new HashMap<>();

        System.out.println("\n\nADD");

        StopWatch watch = new StopWatch();
        watch.start();
        for (long i = 0; i < size; i++) {
            set.add(new Neuron(i, null, null));
        }
        watch.stop();
        System.out.println("ADD SET: "+ watch.getLastTaskTimeMillis());

        watch.start();
        for (long i = 0; i < size; i++) {
            linkset.add(new Neuron(i, null, null));
        }
        watch.stop();
        System.out.println("ADD LSET: "+ watch.getLastTaskTimeMillis());

        watch.start();
        for (long i = 0; i < size; i++) {
            map.put(i, new Neuron(i, null, null));
        }
        watch.stop();
        System.out.println("ADD MAP: "+ watch.getLastTaskTimeMillis());


        System.out.println("\n\nCONTAINS");

        watch.start();
        for (long i = 0; i < size; i++) {
            set.contains(new Neuron(i, null, null));
        }
        watch.stop();
        System.out.println("CONTAINS SET: "+ watch.getLastTaskTimeMillis());

        watch.start();
        for (long i = 0; i < size; i++) {
            linkset.contains(new Neuron(i, null, null));
        }
        watch.stop();
        System.out.println("CONTAINS LSET: "+ watch.getLastTaskTimeMillis());

        watch.start();
        for (long i = 0; i < size; i++) {
            map.containsKey(new Neuron(i, null, null).getInnovation());
        }
        watch.stop();
        System.out.println("CONTAINS MAP: "+ watch.getLastTaskTimeMillis());


        System.out.println("\n\nITERATE");

        int total = 0;
        watch.start();
        for (Neuron s : set) {
            total += s.getInnovation();
        }
        watch.stop();
        System.out.println("ITERATE SET: "+ watch.getLastTaskTimeMillis() + " - total: "+ total);

        total = 0;
        watch.start();
        for (Neuron s : linkset) {
            total += s.getInnovation();
        }
        watch.stop();
        System.out.println("ITERATE LSET: "+ watch.getLastTaskTimeMillis() + " - total: "+ total);

        total = 0;
        watch.start();
        for (Neuron m : map.values()) {
            total += m.getInnovation();
        }
        watch.stop();
        System.out.println("ITERATE MAP: "+ watch.getLastTaskTimeMillis() + " - total: "+ total);


        System.out.println("\n\nFIND X GET");

        total = 0;
        watch.start();
        for (long i = 0; i < size; i++) {
            total += find(i, set);
        }
        watch.stop();
        System.out.println("FIND SET: "+ watch.getLastTaskTimeMillis() + " - total: "+ total);

        total = 0;
        watch.start();
        for (long i = 0; i < size; i++) {
            total += find(i, linkset);
        }
        watch.stop();
        System.out.println("FIND LSET: "+ watch.getLastTaskTimeMillis() + " - total: "+ total);

        total = 0;
        watch.start();
        for (long i = 0; i < size; i++) {
            total += map.get(i).getInnovation();
        }
        watch.stop();
        System.out.println("GET1 MAP: "+ watch.getLastTaskTimeMillis() + " - total: "+ total);

        total = 0;
        watch.start();
        for (Long m : map.keySet()) {
            total += map.get(m).getInnovation();
        }
        watch.stop();
        System.out.println("GET2 MAP: "+ watch.getLastTaskTimeMillis() + " - total: "+ total);


        Map<Long, Neuron> map2 = new HashMap<>(MapUtils.getSize(size));

        total = 0;
        watch.start();
        for (Neuron s : linkset) {
            map2.put(s.getInnovation(), s);
        }
        for (Long m : map2.keySet()) {
            total += map2.get(m).getInnovation();
        }
        watch.stop();
        System.out.println("COPY LINKSET TO MAP AND GET: "+ watch.getLastTaskTimeMillis() + " - total: "+ total);


        map2 = new HashMap<>(MapUtils.getSize(size));

        total = 0;
        watch.start();
        for (Neuron s : linkset) {
            map2.put(s.getInnovation(), new Neuron(s));
        }
        for (Long m : map2.keySet()) {
            total += map2.get(m).getInnovation();
        }
        watch.stop();
        System.out.println("CLONE LINKSET TO MAP AND GET: "+ watch.getLastTaskTimeMillis() + " - total: "+ total);


        System.out.println("\n\nREMOVE");

        watch = new StopWatch();
        watch.start();
        for (long i = 0; i < size; i++) {
            set.remove(new Neuron(i, null, null));
        }
        watch.stop();
        System.out.println("REMOVE SET: "+ watch.getLastTaskTimeMillis());

        watch.start();
        for (long i = 0; i < size; i++) {
            linkset.remove(new Neuron(i, null, null));
        }
        watch.stop();
        System.out.println("REMOVE LSET: "+ watch.getLastTaskTimeMillis());

        watch.start();
        for (long i = 0; i < size; i++) {
            map.remove(i);
        }
        watch.stop();
        System.out.println("REMOVE MAP: "+ watch.getLastTaskTimeMillis());

    }

    public static long find(long value, Set<Neuron> set) {
        for (Neuron s : set) {
            if (s.getInnovation().equals(value)) {
                return value;
            }
        }
        return 0;
    }

}
