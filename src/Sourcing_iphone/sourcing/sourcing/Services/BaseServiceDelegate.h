//
//  BaseServiceDelegate.h
//
//  Created by wuzhu on 12-6-28.
//  Copyright (c) 2012年 __MyCompanyName__. All rights reserved.
//

@class ResponseVO;
@protocol BaseServiceDelegate

@optional

/**
 * service结果
 * @param result  结果
 */
-(void)serviceResult:(ResponseVO *)result;

@end
