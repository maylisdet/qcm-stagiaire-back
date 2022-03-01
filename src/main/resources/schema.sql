create procedure get_record_ranking(IN recordId INT, IN quizId INT)
BEGIN
    select a.score as best_score, b.duration_of_best_score, c.nb_respondent, d.score_rank from ( select best.best_score as score  from (select max(score) as best_score from record) best) a,
        (select t.duration_of_best_score from (select min(duration) as duration_of_best_score from record where score = (select max(score) from record where quiz_id = quizId) and quiz_id = quizId) t) b,
        (select count(distinct user_id) as nb_respondent  from record where quiz_id = quizId)c,
        (select ranking as score_rank
        from (select id, rank() over (order by score desc) as ranking  from record where quiz_id = quizId) record_rankings
        where record_rankings.id = recordId) d;
end ^;
