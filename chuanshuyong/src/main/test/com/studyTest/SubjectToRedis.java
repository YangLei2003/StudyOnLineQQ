import cn.hutool.json.JSONUtil;
import com.rabbiter.ol.OnlineLearnApplication;
import com.rabbiter.ol.dao.SubjectDao;
import com.rabbiter.ol.entity.RedisData;
import com.rabbiter.ol.entity.SubjectEntity;
import com.rabbiter.ol.service.SubjectService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.annotation.Resource;
import javax.security.auth.Subject;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest(classes= OnlineLearnApplication.class)
public class SubjectToRedis {
    @Resource
    private SubjectService subjectService;
    @Resource
    private SubjectDao subjectDao;
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Test
    public void loadSubjectToRedis(){
        List<SubjectEntity> subjectList= subjectDao.getAllSubject();
        for(SubjectEntity subjectEntity:subjectList){
            RedisData redisData=new RedisData();
            redisData.setData(subjectEntity);
            redisData.setExpireTime(LocalDateTime.now().plusHours(24));
            String subjectWithExpireTime= JSONUtil.toJsonStr(redisData);
            stringRedisTemplate.opsForValue().set("cache:subject_key:"+subjectEntity.getId(),subjectWithExpireTime);
        }
    }
}
