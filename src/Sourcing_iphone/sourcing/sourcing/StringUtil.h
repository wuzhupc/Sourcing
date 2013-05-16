//
//  StringUtil.h
//  EmbaClientForiPhone
//
//  Created by wuzhu on 12-5-9.
//  Copyright (c) 2012年 __MyCompanyName__. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface StringUtil : NSObject

/**
 *判断字符串是否为空 包括nil及空字符串
 *@param str 要判断的字符串
 *@return 为空返回YES 否则返回NO
 */
+(BOOL) isEmptyStr:(NSString *) str;

/**
 *判断是否为空
 */
+(BOOL)isEmpty:(id) thing;

+ (NSMutableString *) initMutableStr:(NSString *)str;

//字符串转BOOL
//如果str=="true"或str=="1"，则返回YES 否则返回NO
+(BOOL)strToBOOL:(NSString *)str;
@end
