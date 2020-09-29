package ru.smak.math.polynoms;

import java.util.HashMap;

public class Newton extends Polynom {

    private static class Pair{

        public Integer first;
        public Integer second;

        public Pair(Integer a, Integer b){
            first = a;
            second = b;
        }

        @Override
        public boolean equals(Object other){
            if (other.getClass()!=getClass()) return false;
            return (((Pair)other).first.equals(first) &&
                    ((Pair)other).second.equals(second));
        }

        @Override
        public int hashCode(){
            return first.hashCode()+second.hashCode();
        }

    }

    private final HashMap<Pair, Double> dd = new HashMap<>();
    private final HashMap<Double, Double> vals;
    private Polynom fundamental = new Polynom(new double[] {1.0});

    public Newton(HashMap<Double, Double> vals){
        this.vals = new HashMap<>();
        if (vals.size() > 0){
            int i = 0;
            for (Double k: vals.keySet()) {
                dd.putIfAbsent(new Pair(i, i), vals.get(k));
                i++;
            }
            createNewton(vals);
        }
    }

    private void createNewton(HashMap<Double, Double> vals) {
        for (var k : vals.keySet()){
            add(k, vals.get(k));
        }
    }

    private Double razdRazn(int n1, int n2){
        Pair ind = new Pair(n1, n2);
        if (dd.containsKey(ind))
            return dd.get(ind);
        Double []keys = new Double[0];
        keys = vals.keySet().toArray(keys);
        double rr = (razdRazn(n1 + 1, n2) - razdRazn(n1, n2-1)) /
                    (keys[n2] - keys[n1]);
        dd.put(ind, rr);
        return rr;
    }

    public void add(Double x, Double y){
        if (!vals.containsKey(x)){
            vals.put(x, y);
            dd.putIfAbsent(new Pair(vals.size()-1, vals.size()-1), y);
            Polynom p = this;
            p = p.plus(fundamental.times(razdRazn(0, vals.size() - 1)));
            fundamental = fundamental.times(new Polynom(new double[]{-x, 1.0}));
            coef = p.coef.clone();
        }
    }
}