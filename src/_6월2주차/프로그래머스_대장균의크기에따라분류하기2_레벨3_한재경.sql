-- 윈도우함수, PERCENT_RANK() 이용

-- 대장균 개체의 크기를 내름차순으로 정렬했을 때
-- 상위 0% ~ 25% 를 'CRITICAL', 26% ~ 50% 를 'HIGH',
-- 51% ~ 75% 를 'MEDIUM', 76% ~ 100% 를 'LOW' 라고 분류
-- 대장균 개체의 ID(ID) 와 분류된 이름(COLONY_NAME)을 출력
SELECT ID, (CASE
           WHEN S.CR <= 0.25 THEN 'CRITICAL'
           WHEN S.CR <= 0.5 THEN 'HIGH'
           WHEN S.CR <= 0.75 THEN 'MEDIUM'
           ELSE 'LOW'
           END) AS 'COLONY_NAME'
FROM (SELECT ID, PERCENT_RANK() OVER(ORDER BY SIZE_OF_COLONY DESC) AS 'CR'
     FROM ECOLI_DATA) S
ORDER BY ID;
