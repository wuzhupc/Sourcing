//
//  NetWorkUtil.h
//  EmbaClientForiPhone
//
//  Created by wuzhu on 12-6-7.
//  Copyright (c) 2012年 __MyCompanyName__. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface NetWorkUtil : NSObject



/**
 * 是否能连接到服务器
 */
+(BOOL) activeHostNetWork;

//是否能连接到网络
+(BOOL) activeInternetNetWork;


+(BOOL) checkNetWork:(BOOL) showHit;
@end
