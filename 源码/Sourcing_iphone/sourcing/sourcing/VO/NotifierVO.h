//
//  NotifierVO.h
//  sourcing
//
//  Created by wuzhu on 12-12-26.
//  Copyright (c) 2012年 wuzhu. All rights reserved.
//

#import "BaseVO.h"

@interface NotifierVO : BaseVO
{
    NSString *notifierid;
    NSString *notifiercontent;
    NSString *publisher;
    NSString *publishtime;
}

@property NSString *notifiercontent;
@property NSString *publisher;
@property NSString *publishtime;

-(void)setNotifierid:(NSString *)kid;
-(NSString *)Notifierid;

@end
