//
//  ProjectDetailVO.m
//  sourcing
//
//  Created by wuzhu on 12-12-26.
//  Copyright (c) 2012年 wuzhu. All rights reserved.
//

#import "ProjectDetailVO.h"
#import "HtmlUtil.h"

@implementation ProjectDetailVO

@synthesize projectcontent = projectcontent_;

#pragma mark - Superclass Overrides
-(NSString *)getHtmlContext
{
    return [HtmlUtil getHtmlContext:self.projectcontent];
}
@end
