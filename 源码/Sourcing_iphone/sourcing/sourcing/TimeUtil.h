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
+(NSString *)getGMTFormateDate:(NSDate *)localDate;
+(NSString *)getDetailGMTDate:(NSDate *)localDate;
@end
