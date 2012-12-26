//
//  DataInterfaceUtil.h
//  EmbaClientForiPhone
//
//  Created by wuzhu on 12-5-9.
//  Copyright (c) 2012年 __MyCompanyName__. All rights reserved.
//

#import <Foundation/Foundation.h>

/**
 * 数据接口信息获取工具类
 */
@interface DataInterfaceUtil : NSObject


//获取指定key对应的数据接口信息
+ (NSString *)  getDataInterface:(NSString *) paramKey;

+ (NSString *) getServerUrl;
@end
