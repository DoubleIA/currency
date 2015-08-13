package com.doubleia.currency.chp3;


/**
 * 
 * @date 2015.8.4 15:57
 * @author wangyingbo
 * @description 使用FinalField_OneValueCache来保存返回的数值及其因数，
 *	@version 3.0.4
 *
 *使用volatile保证cache的可见性，使用包含多个状态的容器对象来维持不变性条件。
 *
 */
/*
public class FinalField_VolatileCachedFactorizer implements Servlet {

	private volatile FinalField_OneValueCache cache = new FinalField_OneValueCache(null, null);
	
	public void service(ServletRequest req, ServletResponse res)
			throws ServletException, IOException {
		BigInteger i = extractFromRequest(req);
		BigInteger[] factors = cache.getFactors(i);
		if (factors == null) {
			factors = factor(i);
			cache = new FinalField_OneValueCache(i, factors);
		}
		encodeIntoResponse(res, factors);	
	}

	public void destroy() {
		
	}

	public ServletConfig getServletConfig() {
		return null;
	}

	public String getServletInfo() {
		return null;
	}

	public void init(ServletConfig arg0) throws ServletException {
		
	}

}
*/