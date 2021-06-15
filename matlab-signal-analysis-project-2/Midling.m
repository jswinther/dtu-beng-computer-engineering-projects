function [] = Midling(input, n)

a = zeros(length(input));
figure,plot(input), title({'De oprindelige data ', n});

for r = 1:length(input)-17
    num = 0;
    for i = 1:17
       num = num + input(r+i); 
    end
    a(r) = num/17;
end
b = a(1:length(input)-17);
figure,plot(b), title( {'Midling for 17 værdier ', n});

a = zeros(length(input));
for r = 1:length(input)-36
    num = 0;
    for i = 1:36
       num = num + input(r+i); 
    end
    a(r) = num/36;
end
b = a(1:length(input)-36);
figure,plot(b), title( {'Midling for 36 værdier ', n})

a = zeros(length(input));
for r = 1:length(input)-150
    num = 0;
    for i = 1:150
       num = num + input(r+i); 
    end
    a(r) = num/150;
end
b = a(1:length(input)-150);
figure,plot(b);
title({'Midling for 150 værdier ', n})

a = zeros(length(input));
for r = 1:length(input)-300
    num = 0;
    for i = 1:300
       num = num + input(r+i); 
    end
    a(r) = num/300;
end
b = a(1:length(input)-300);
figure,plot(b);
title({'Midling for 300 værdier ', n})
end

