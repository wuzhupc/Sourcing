//
//  TrainDetailVO.m
//  sourcing
//
//  Created by wuzhu on 12-12-26.
//  Copyright (c) 2012年 wuzhu. All rights reserved.
//

#import "TrainDetailVO.h"
#import "HtmlUtil.h"

@implementation TrainDetailVO
@synthesize traincontent = traincontent_;

#pragma mark - Superclass Overrides
-(NSString *)getHtmlContext
{
    return [HtmlUtil getHtmlContext:self.traincontent];
}
@end
