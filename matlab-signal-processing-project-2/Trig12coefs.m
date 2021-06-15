function As = Trig12coefs(Us)
%
% Computes 12 coefficients from 12 data points
%
% The data points are 12 measurements taken in equidistant points
% The coefficients express
%
%    a0 + a1*cos(x) + a2*sin(x) + a3*cos(2x) + ...
%
% i.e. a periodic function
%
    v1 = Us(2) + Us(12);
    v2 = Us(3) + Us(11);
    v3 = Us(4) + Us(10);
    v4 = Us(5) + Us(9);
    v5 = Us(6) + Us(8);
    
    w1 = Us(2) - Us(12);
    w2 = Us(3) - Us(11);
    w3 = Us(4) - Us(10);
    w4 = Us(5) - Us(9);
    w5 = Us(6) - Us(8);
        
    p0 = Us(1) + Us(7);
    p1 = v1 + v5;
    p2 = v2 + v4;
    p3 = v3;
    
    q0 = Us(1) - Us(7);
    q1 = v1 - v5;
    q2 = v2 - v4;
    
    r1 = w1 + w5;
    r2 = w2 + w4;
    r3 = w3;
    
    s1 = w1 - w5;
    s2 = w2 - w4;
    
    t = 0.5*sqrt(3);
    
    As(1) = (p0 + p1 + p2 + p3)/12.0; %gennesnit af alle 12 værdier, da alt er +
    As(3) = (0.5*r1 + t*r2 + r3)/6.0; 
    As(5) = t*(s1 + s2)/6.0;
    As(7) = (r1 - r3)/6.0;
    As(9) = t*(s1 - s2)/6.0;
    As(11) = (0.5*r1 - t*r2 + r3)/6.0;
    As(12) = (p0 - p1 + p2 - p3)/12.0;

    As(2) = (q0 + t*q1 + 0.5*q2)/6.0;
    As(4) = (p0 + 0.5*p1 - 0.5*p2 - p3)/6;    
    As(6) = (q0 - q2)/6.0;    
    As(8) = (p0 - 0.5*p1 - 0.5*p2 + p3)/6.0;
    As(10) = (q0 - t*q1 + 0.5*q2)/6.0;        
end