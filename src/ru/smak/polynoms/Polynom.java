package ru.smak.polynoms;

public class Polynom {
    /**
     * Коэффициенты полинома
     */
    protected double [] coef;
    public static final double ZERO = 1e-24;

    /**
     * Инициализация полинома нулевой степени со значением 0
     */
    public Polynom(){
        coef = new double[1];
        coef[0] = 0.0;
    }

    /**
     * Создание полинома на базе набора коэффициентов
     * с корректировкой его степени при необходимости
     * @param c коэффициенты создаваемого полинома
     */
    public Polynom(double[] c){
        coef = c.clone();
        correctPower();
    }

    /**
     * Получение степени полинома
     * @return целочисленное значение степени полинома
     */
    public int getPower(){
        return coef.length - 1;
    }

    /**
     * Метод корректировки степени полинома в зависимости от числа
     * нулевых коэффициентов при старших степенях
     */
    private void correctPower(){
        int rem = coef.length;
        while (Math.abs(coef[rem-1])<ZERO && rem > 1){
            rem--;
        }
        double [] c = new double[rem];
        System.arraycopy(coef, 0, c, 0, rem);
        coef = c;
    }

    /**
     * Сложение двух полиномов
     * @param other второй (правый) полином
     * @return полином, являющийся результатом суммирования данного полинома с другим
     */
    public Polynom plus(Polynom other){
        Polynom minP, maxP;
        if (coef.length>other.coef.length){
            minP = other;
            maxP = this;
        } else {
            minP = this;
            maxP = other;
        }
        double [] c = new double[maxP.coef.length];
        for (int i = 0; i<minP.coef.length; i++){
            c[i] = minP.coef[i] + maxP.coef[i];
        }
        if (maxP.coef.length - minP.coef.length >= 0)
            System.arraycopy(
                    maxP.coef,
                    minP.coef.length,
                    c,
                    minP.coef.length,
                    maxP.coef.length - minP.coef.length);
        return new Polynom(c);
    }

    /**
     * Определение разности двух полиномов
     * @param other вычитаемый полином
     * @return разность данного (this) и второго (other) полиномов
     */
    public Polynom minus(Polynom other){
        return this.plus(other.times(-1.0));
    }

    /**
     * Определение произведения двух полиномов
     * @param other второй полином
     * @return произведение данного (this) и второго (other) полиномов
     */
    public Polynom times(Polynom other){
        // Создание массива нужной длины для коэффициентов нового полинома
        double[] c = new double[getPower() + other.getPower() + 1];
        // Вычисление значение новых коэффициентов
        for (int i = 0; i<coef.length; i++) {
            for (int j = 0; j<other.coef.length; j++) {
                c[i + j] += coef[i] * other.coef[j];
            }
        }
        // Создание нового полинома по рассчитанным коэффициентам
        return new Polynom(c);
    }

    /**
     * Определение значения произведения полинома на число
     * @param k вещественный коэффициент
     * @return результат умножения данного (this) полинома на число
     */
    public Polynom times(double k){
        double []c = coef.clone();
        for (int i = 0; i<coef.length; i++){
            c[i] *= k;
        }
        return new Polynom(c);
    }

    /**
     * Определение значения частного от деления полинома на число
     * @param k вещественный коэффициент
     * @return результат деления данного (this) полинома на число
     */
    public Polynom div(double k) throws Exception{
        if (Math.abs(k) < ZERO) throw new Exception("Деление на 0");
        return this.times(1./k);
    }

    public double invoke(double x){
        double p = 0;
        double mon = 1;
        for (double v : coef) {
            p += mon * v;
            mon *= x;
        }
        return p;
    }

    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        for (int i = coef.length-1; i>=0; i--) {
            if (Math.abs(coef[i]) < ZERO && (i!=0 || coef.length!=1)) continue;
            double c = coef[i];
            if (coef[i] < 0) {
                res.append("-");
                c = Math.abs(c);
            } else
                if (i < coef.length - 1)
                    res.append("+");
            if (Math.abs(c-1) > ZERO || i == 0)
                if (Math.abs(c-(long)c)<ZERO)
                    res.append((long)c);
                else
                    res.append(c);
            if (i >= 1) res.append('x');
            if (i > 1) {
                res.append('^');
                res.append(i);
            }
        }
        return res.toString();
    }


}
