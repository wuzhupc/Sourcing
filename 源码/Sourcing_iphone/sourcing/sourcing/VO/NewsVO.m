//
//  NewsVO.m
//  sourcing
//
//  Created by wuzhu on 12-12-26.
//  Copyright (c) 2012年 wuzhu. All rights reserved.
//

#import "NewsVO.h"

@implementation NewsVO

@synthesize headline = headline;
@synthesize newssummary = newssummary;
@synthesize title = title;
@synthesize titlepic = titlepic;
@synthesize newstype = newstype;
@synthesize titlepic_small = titlepic_small;
@synthesize auther = auther;
@synthesize readernum = readernum;
@synthesize commentnum = commentnum;

-(void)setNewsid:(NSString *)kid
{
    newsid = kid;
    _id = kid;
}
-(NSString *)Newsid
{
    return newsid;
}
@end
