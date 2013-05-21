//
//  TimeUtil.m
//  sourcing
//
//  Created by wuzhu on 12-12-25.
//  Copyright (c) 2012年 wuzhu. All rights reserved.
//

#import "TimeUtil.h"
#import "StringUtil.h"

@implementation TimeUtil
//获取自1970年以来的毫秒数
+(long long int) currentTimeMillis
{
    NSTimeInterval time = [[NSDate date] timeIntervalSince1970]*1000;
    
    return (long long int)time;
}

+ (NSDate *)dateFromStringWithFormat:(NSString *)dateString format:(NSString *)kformat
{
    if([StringUtil isEmpty:dateString])
        return nil;
    NSDateFormatter *dateFormatter = [[NSDateFormatter alloc] init];
    if([StringUtil isEmpty:kformat])
        [dateFormatter setDateFormat: @"yyyy-MM-dd HH:mm:ss"];
    else
        [dateFormatter setDateFormat: kformat];
    NSDate *destDate= [dateFormatter dateFromString:dateString];
    return destDate;
}

+ (NSDate *)dateFromString:(NSString *)dateString{
    return [TimeUtil dateFromStringWithFormat:dateString format:@"yyyy-MM-dd HH:mm:ss"];
}

+ (NSString *)stringFromDateWithFormat:(NSDate *)date format:(NSString *)kformat
{
    if(date == nil)
        return @"";
    NSDateFormatter *dateFormatter = [[NSDateFormatter alloc] init];
    if([StringUtil isEmpty:kformat])
    {
        //zzz表示时区，zzz可以删除，这样返回的日期字符将不包含时区信息 +0000。
        [dateFormatter setDateFormat:@"yyyy-MM-dd HH:mm:ss"];
        return [dateFormatter stringFromDate:date];
    }
    [dateFormatter setDateFormat:kformat];
    return [dateFormatter stringFromDate:date];
}

+ (NSString *)stringFromDate:(NSDate *)date{
    
    return [TimeUtil stringFromDateWithFormat:date format:@"yyyy-MM-dd HH:mm:ss"];
}
@end
