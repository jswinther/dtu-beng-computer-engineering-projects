%
% Plotting periodic phenomena
%
Odour_data
plot(Median)
title( 'The median odour release from pig farm')
pause



As = Trig12coefs(Median);
Fs = Trig12Eval(0:1/10:12, As, 0, 12);
plot(Fs)
title( 'The periodic interpolation')
pause

%
% OBS: Note the interval specifications
%
plot(0:11, Median, 'o', 0:1/10:12, Fs, '-')
title( 'Data and interpolant together' )
pause

%
% Plotting several cycles
%
% Note: tmin and tmax are still the interval over which the
% original interpolation took place.
% The evaluation interval, however, is extended
%
Fs = Trig12Eval(0:1/10:48, As, 0, 12);
plot(0:11, Median, 'o', 0:1/10:48, Fs, '-')
title( 'Four cycles of interpolation, one cycle of data')
pause

fid = fopen( 'Three years ozone data.txt', 'r');
Ozone = zeros(1,36);
Ozone = fscanf(fid, '%f', 36);
plot(Ozone)
title('Three years of ozone measurements')
pause

Az = Trig12coefs(Ozone(13:24));
Fz = Trig12Eval(12:1/10:24, Az, 12, 24);
plot(0:35, Ozone, 'o', 12:1/10:24, Fz, '-')
title( 'Interpolating the middle section')
pause

Fx = Trig12Eval(0:1/10:36, Az, 12, 24);
plot(0:35, Ozone, 'o', 0:1/10:36, Fx, '-')
title( 'Extending the interpolation function')