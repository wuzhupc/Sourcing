//
//  NewsVO.m
//  sourcing
//
//  Created by wuzhu on 12-12-26.
//  Copyright (c) 2012年 wuzhu. All rights reserved.
//

#import "NewsVO.h"
#import "StringUtil.h"

@implementation NewsVO

@synthesize headline = headline;
@synthesize newssummary = newssummary;
@synthesize title = title;
@synthesize titlepic = titlepic;
@synthesize newstype = newstype;
@synthesize titlepic_small = titlepic_small;
@synthesize publishtime = publishtime;
@synthesize auther = auther;
@synthesize source = source;
@synthesize readernum = readernum;
@synthesize commentnum = commentnum;

-(void)setNewsid:(NSInteger )kid
{
    newsid = kid;
    _id = kid;
}
-(NSInteger )Newsid
{
    return newsid;
}

-(BOOL)isHeadline
{
    if([StringUtil isEmpty:headline])
        return NO;
    return [headline isEqualToString:@"1"] || [[headline lowercaseString] isEqualToString:@"true"];
}
@end
