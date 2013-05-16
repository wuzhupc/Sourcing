//
//  TimeUtil.h
//  sourcing
//
//  Created by wuzhu on 12-12-25.
//  Copyright (c) 2012年 wuzhu. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface TimeUtil : NSObject
+(long long int) currentTimeMillis;
+ (NSDate *)dateFromStringWithFormat:(NSString *)dateString format:(NSString *)kformat;
+ (NSDate *)dateFromString:(NSString *)dateString;
+ (NSString *)stringFromDateWithFormat:(NSDate *)date format:(NSString *)kformat;
+ (NSString *)stringFromDate:(NSDate *)date;
@end
