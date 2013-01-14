//
//  ResumeDetailVO.m
//  sourcing
//
//  Created by wuzhu on 12-12-26.
//  Copyright (c) 2012年 wuzhu. All rights reserved.
//

#import "ResumeDetailVO.h"
#import "HtmlUtil.h"

@implementation ResumeDetailVO
@synthesize resumecontent = resumecontent_;

#pragma mark - Superclass Overrides
-(NSString *)getHtmlContext
{
    return [HtmlUtil getHtmlContext:self.resumecontent];
}
@end
