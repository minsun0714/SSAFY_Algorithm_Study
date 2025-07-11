-- 코드를 작성해주세요
SELECT ID,
    CASE
        WHEN b.RNK <= 0.25 * COUNT(*) OVER()
            THEN 'CRITICAL'
        WHEN b.RNK <= 0.5 * COUNT(*) OVER()
            THEN 'HIGH'
        WHEN b.RNK <= 0.75 * COUNT(*) OVER()
            THEN 'MEDIUM'
        WHEN b.RNK <= COUNT(*) OVER()
            THEN 'LOW'
        END AS COLONY_NAME
    FROM
        (SELECT
            ID, RANK() OVER(ORDER BY SIZE_OF_COLONY DESC) RNK
         FROM ECOLI_DATA) b
    ORDER BY b.ID;