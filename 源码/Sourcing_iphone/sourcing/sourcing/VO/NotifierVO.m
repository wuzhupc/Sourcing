//
//  NotifierVO.m
//  sourcing
//
//  Created by wuzhu on 12-12-26.
//  Copyright (c) 2012年 wuzhu. All rights reserved.
//

#import "NotifierVO.h"

@implementation NotifierVO

@synthesize notifiercontent = notifiercontent;
@synthesize publisher = publisher;
@synthesize publishtime = publishtime;

-(void)setNotifierid:(NSInteger )kid
{
    _id = kid;
    notifierid = kid;
}
-(NSInteger )Notifierid
{
    return notifierid;
}

@end
