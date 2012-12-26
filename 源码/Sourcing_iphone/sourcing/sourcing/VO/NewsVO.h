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
    NSString *newsid;
    NSString *headline;
    NSString *newstype;
    NSString *title;
    NSString *titlepic;
    NSString *titlepic_small;
    NSString *newssummary;
    NSString *auther;
    NSString *readernum;
    NSString *commentnum;
}
@property NSString *headline;
@property NSString *newstype;
@property NSString *title;
@property NSString *titlepic;
@property NSString *titlepic_small;
@property NSString *newssummary;
@property NSString *auther;
@property NSString *readernum;
@property NSString *commentnum;

-(void)setNewsid:(NSString *)kid;
-(NSString *)Newsid;

@end
