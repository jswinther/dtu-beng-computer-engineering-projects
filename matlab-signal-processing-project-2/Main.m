%Datasæt indlæses fra filer
Threeyearsozonedata = importdata('Three years ozone data.txt');
fid1 = fopen('Reykjavik data v2.txt');
a = textscan(fid1, '%s  %f');
fclose(fid1);
Reykjavikdata=a{2};
fid2 = fopen('Brest data v2.txt');
a = textscan(fid2, '%s  %f');
fclose(fid2);
Brestdata=a{2};
ozonedepletingsubstanceemissions = csvread('ozone-depleting-substance-emissions.csv',73,3);
stratosphericozoneconcentration = csvread('stratospheric-ozone-concentration.csv',1,4);

%Names
n1 = ' Three years of ozone';
n2 = ' Reykjavik data';
n3 = ' Brest data';
n4 = ' stratospheric ozone concentration';
n5 = ' ozone depleting substance emissions';



%Originale data plottes
figure, plot(Threeyearsozonedata), title('Original data fra Threeyearsozonedata');
figure, plot(Reykjavikdata), title('Original data fra Reykjavikdata');
figure, plot(Brestdata), title('Original data fra Brestdata');
figure, plot(ozonedepletingsubstanceemissions), title('Original data fra ozonedepletingsubstanceemissions');
figure, plot(stratosphericozoneconcentration), title('Original data fra stratosphericozoneconcentration');


%Effektspectrum plots
Effektspektrum(Threeyearsozonedata, n1);
Effektspektrum(Reykjavikdata, n2);
Effektspektrum(Brestdata, n3);
Effektspektrum(stratosphericozoneconcentration, n4);
Effektspektrum(ozonedepletingsubstanceemissions, n5);


%Gauss plots
Gauss(Threeyearsozonedata, n1);
Gauss(Reykjavikdata, n2);
Gauss(Brestdata, n3);
Gauss(stratosphericozoneconcentration, n4);
Gauss(ozonedepletingsubstanceemissions, n5);


%Midling plots
Midling(Threeyearsozonedata, n1);
Midling(Reykjavikdata, n2);
Midling(Brestdata, n3);
Midling(stratosphericozoneconcentration, n4);
Midling(ozonedepletingsubstanceemissions, n5);




