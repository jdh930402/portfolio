package my.shop.portfolio.aspect;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoginAspect {
	
	@Autowired
	@Qualifier("loginClass")
	private LoginClass loginClass;
	
	@Around("execution(* member*(..))")
	public Object aopProcess(ProceedingJoinPoint joinPoint) throws Throwable{
		HttpServletRequest request = null;
		HttpServletResponse response = null;
		
		for(Object obj:joinPoint.getArgs()) {
			if(obj instanceof HttpServletRequest) {
				request = (HttpServletRequest)obj;
			}
	
			if(obj instanceof HttpServletResponse) {
				response = (HttpServletResponse)obj;
			}
		}
		
		// session 체크 후 로그인 하지 않은 경우 로그인 창으로 이동하므로 핵심로직 수행 불가 처리 
		if(loginClass.loginCheck(request,response)) {
			return null;
		}
		
		// 핵심로직 수행
		Object object = joinPoint.proceed();
		return object;
	}
}
