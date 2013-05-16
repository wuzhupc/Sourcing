//
//  DataInterfaceUtil.m
//  EmbaClientForiPhone
//
//  Created by wuzhu on 12-5-9.
//  Copyright (c) 2012年 __MyCompanyName__. All rights reserved.
//

#import "DataInterfaceUtil.h"
#import "StringUtil.h"

@implementation DataInterfaceUtil

/**
 * 获取指定key对应的数据接口信息
 * @param paramKey 
 */
+ (NSString *) getDataInterface:(NSString *)paramKey
{
    if ([StringUtil isEmptyStr:paramKey]) {
        return nil;
    }
    NSString *plistPath = [[NSBundle mainBundle] pathForResource:@"DataInterface" ofType:@"plist"];
    //读取文件
    NSMutableDictionary *dict = [[NSMutableDictionary alloc] initWithContentsOfFile:plistPath];
    return [dict objectForKey:paramKey];
    /*
     存取     
     NSMutableDictionary *dict = [[NSMutableDictionary alloc] initWithContentsOfFile:DataInterfaceFile];
     [dict setObject:@"Value" forKey:@"KEY"];
     [dict writeToFile:DataInterfaceFile atomically:YES];
     */
}

/**
 * 获取数据接口URL地址
 **/
+ (NSString *) getServerUrl
{
    return [DataInterfaceUtil getDataInterface:@"jsonurl"];
}

@end
