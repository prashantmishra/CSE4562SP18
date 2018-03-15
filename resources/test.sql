CREATE TABLE R(A INTEGER, B INTEGER);
CREATE TABLE S(D INTEGER, E INTEGER, F DATE);

SELECT A, B FROM R;
SELECT A+B FROM R;
SELECT G FROM (SELECT A+B AS G FROM R);


SELECT * FROM R,S;
SELECT * FROM R ORDER BY A DESC;

SELECT * FROM R ORDER BY A DESC LIMIT 2;
SELECT * FROM R,S LIMIT 2;

SELECT * FROM R,S WHERE R.A > 3;