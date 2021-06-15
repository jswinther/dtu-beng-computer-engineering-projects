function [] = Gauss(input, n)



As = Trig12coefs(input);
figure,plot(As(2:12)), title( {'Oprindelige data fra Trig12coefs ', n});

Fs = Trig12Eval(0:9/10:12, As, 0, 12);
figure,plot(Fs), title( {'The periodic interpolation 9/10 ', n});

Fs = Trig12Eval(0:7/10:12, As, 0, 12);
figure,plot(Fs), title( {'The periodic interpolation 7/10 ', n});

Fs = Trig12Eval(0:5/10:12, As, 0, 12);
figure,plot(Fs), title( {'The periodic interpolation 5/10 ', n});

Fs = Trig12Eval(0:3/10:12, As, 0, 12);
figure,plot(Fs), title( {'The periodic interpolation 3/10 ', n});

Fs = Trig12Eval(0:1/10:12, As, 0, 12);
figure,plot(Fs), title( {'The periodic interpolation 1/10 ', n});
end