//
//  NotifierVO.m
//  sourcing
//
//  Created by wuzhu on 12-12-26.
//  Copyright (c) 2012年 wuzhu. All rights reserved.
//

#import "NotifierVO.h"

@interface NotifierVO  ()
{
@private
    NSInteger notifierid;
}
@end

@implementation NotifierVO

@synthesize notifiercontent = notifiercontent_;
@synthesize publisher = publisher_;
@synthesize publishtime = publishtime_;

-(void)setNotifierid:(NSInteger )kid
{
    self.Id = kid;
    notifierid = kid;
}
-(NSInteger )Notifierid
{
    return notifierid;
}

@end
