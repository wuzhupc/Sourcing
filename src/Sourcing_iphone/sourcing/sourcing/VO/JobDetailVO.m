//
//  JobDetailVO.m
//  sourcing
//
//  Created by wuzhu on 12-12-26.
//  Copyright (c) 2012年 wuzhu. All rights reserved.
//

#import "JobDetailVO.h"
#import "HtmlUtil.h"

@implementation JobDetailVO
@synthesize jobdesc = jobdesc_;

#pragma mark - Superclass Overrides
-(NSString *)getHtmlContext
{
    return [HtmlUtil getHtmlContext:self.jobdesc];
}
@end
