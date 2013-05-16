//
//  NewsVO.h
//  sourcing
//
//  Created by wuzhu on 12-12-26.
//  Copyright (c) 2012年 wuzhu. All rights reserved.
//

#import "BaseVO.h"
#import "BaseServiceDelegate.h"

#define CINT_TAG_GETNEWSDETAIL 10003

@interface NewsVO : BaseVO<BaseServiceDelegate>

@property (nonatomic,strong)NSString *headline;
@property (nonatomic)NSInteger newstype;
@property (nonatomic,strong)NSString *title;
@property (nonatomic,strong)NSString *titlepic;
@property (nonatomic,strong)NSString *titlepic_small;
@property (nonatomic,strong)NSString *publishtime;
@property (nonatomic,strong)NSString *newssummary;
@property (nonatomic,strong)NSString *auther;
@property (nonatomic,strong)NSString *source;
@property (nonatomic)NSInteger readernum;
@property (nonatomic)NSInteger commentnum;

-(void)setNewsid:(NSInteger )kid;
-(NSInteger )Newsid;

-(BOOL)isHeadline;

@end
