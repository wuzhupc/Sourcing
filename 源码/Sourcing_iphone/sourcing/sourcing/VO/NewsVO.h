//
//  NewsVO.h
//  sourcing
//
//  Created by wuzhu on 12-12-26.
//  Copyright (c) 2012年 wuzhu. All rights reserved.
//

#import "BaseVO.h"

@interface NewsVO : BaseVO
{
    NSInteger newsid;
    NSString *headline;
    NSInteger newstype;
    NSString *title;
    NSString *titlepic;
    NSString *titlepic_small;
    NSString *publishtime;
    NSString *newssummary;
    NSString *auther;
    NSString *source;
    NSInteger readernum;
    NSInteger commentnum;
    
}
@property NSString *headline;
@property NSInteger newstype;
@property NSString *title;
@property NSString *titlepic;
@property NSString *titlepic_small;
@property NSString *publishtime;
@property NSString *newssummary;
@property NSString *auther;
@property NSString *source;
@property NSInteger readernum;
@property NSInteger commentnum;

-(void)setNewsid:(NSInteger )kid;
-(NSInteger )Newsid;

-(BOOL)isHeadline;

@end
