package thinking.cloud.api.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RestController;

/**
 * controller 的基类
 * <p>controller 的基类</p> 
 * @author think
 */
@RestController
public class BaseController {
	protected Logger logger = LoggerFactory.getLogger(getClass());

}
