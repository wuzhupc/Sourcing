//
//  StringUtil.m
//  EmbaClientForiPhone
//
//  Created by wuzhu on 12-5-9.
//  Copyright (c) 2012年 __MyCompanyName__. All rights reserved.
//

#import "StringUtil.h"

@implementation StringUtil

+ (BOOL) isEmptyStr:(NSString *) str
{
    if (!str) {
        return YES;
    }
    if (str.length==0)
        return YES;
    return NO;
}

+ (NSMutableString *) initMutableStr:(NSString *)str
{
    if([self isEmptyStr:str])
        return nil;
    NSMutableString *result = [NSMutableString stringWithCapacity:[str length]];
    [result appendString:str];
    return result;
}
@end
